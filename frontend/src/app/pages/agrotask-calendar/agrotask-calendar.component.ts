import {Component} from "@angular/core";
import {AppConfig} from "../../app.config";
import {AgroTaskService} from "../../services/agrotask.service";
import "style-loader!fullcalendar/dist/fullcalendar.min.css";
import {AgroTaskModel} from "./agro-task.model";
import {LangService} from "../../services/lang.service";
import {AgroWorkTypeModel} from "./agroworktype.model";
import {ToastrService} from "ngx-toastr";
import {Messages} from "../../common/messages";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'az-agrotask-calendar',
    templateUrl: './agrotask-calendar.component.html',
    styleUrls: ['./agrotask-calendar.component.scss']
})
export class AgroTaskCalendarComponent {

    config: any;

    calendarOptions: any;
    $calendar: any;
    dragOptions: Object = {zIndex: 999, revert: true, revertDuration: 0};

    workTypeModels: AgroWorkTypeModel[];
    agrotaskModels: AgroTaskModel[];

    event: any = {};
    eventForm: FormGroup;
    formSubmitted: boolean = false;

    labelValidationFail: string;
    labelRemoved: string;
    labelSaved: string;

    constructor(private appConfig: AppConfig,
                private fb: FormBuilder,
                private agroTaskService: AgroTaskService,
                private toaStrService: ToastrService,
                private langService: LangService) {
    }

    ngOnInit(): void {
        this.config = this.appConfig.config;

        this.setupLabels();
        this.setupCalendarOptions();
        this.$calendar = jQuery('#calendar');
        this.$calendar.fullCalendar(this.calendarOptions);
        jQuery('.draggable').draggable(this.dragOptions);
        this.findAgroWorkTypes();
        this.findAgroTasks();
    }

    private setupLabels() {
        this.langService.get(Messages.VALIDATION_FAIL).subscribe((message) => this.labelValidationFail = message);
        this.langService.get(Messages.SAVED).subscribe((message) => this.labelSaved = message);
        this.langService.get(Messages.REMOVED).subscribe((message) => this.labelRemoved = message);
    }

    private setupCalendarOptions() {
        this.calendarOptions = {
            locale: this.langService.getLanguage(),
            header: {
                left: 'today prev,next',
                center: 'title',
                right: 'month,agendaWeek,agendaDay,listMonth'
            },
            events: [],
            eventColor: this.config.colors.info,
            selectable: true,
            selectHelper: true,
            select: (start, end) => this.prepareNewEvent(start, end),
            eventClick: (event) => this.prepareEvent(event),
            editable: true,
            droppable: true,

            drop: (dateItem, event): void => { // this function is called when something is dropped
                // retrieve the dropped element's stored Event Object
                let originalEventObject = {
                    // use the element's text as the event title
                    title: jQuery.trim(jQuery(event.target).text())
                };

                // we need to copy it, so that multiple events don't have a reference to the same object
                let copiedEventObject = jQuery.extend({}, originalEventObject);

                // assign it the date that was reported
                copiedEventObject.start = dateItem;
                copiedEventObject.allDay = !dateItem.hasTime();

                let $categoryClass = jQuery(event.target).data('event-class');
                if ($categoryClass) {
                    copiedEventObject.className = [$categoryClass];
                }

                // render the event on the calendar
                // the last `true` argument determines if
                // the event 'sticks'
                // http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                this.$calendar.fullCalendar('renderEvent', copiedEventObject, true);

                jQuery(event.target).remove();

            },
            dayRender: function (date, cell) {
                let today = new Date().toDateString();
                let compareDate = date.toDate().toDateString();
                if (today == compareDate) {
                    cell.css("background-color", "#ccc");
                }
            }
        };
    }

    private findAgroWorkTypes() {
        this.agroTaskService.findWorkTypes().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.workTypeModels = payloadModel.payload;
        })
    }

    private findAgroTasks() {
        this.agroTaskService.find().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.agrotaskModels = payloadModel.payload;

            this.agrotaskModels.forEach((model) => {
                let agroEvent: any = {};
                agroEvent.id = model.id;
                agroEvent.workTypeId = model.workTypeId;
                agroEvent.title = model.title;
                agroEvent.start = new Date(model.scheduledStart);
                agroEvent.end = new Date(model.scheduledEnd);
                agroEvent.backgroundColor = this.getEventBackgroundColor(agroEvent.workTypeId);
                agroEvent.textColor = this.config.colors.default;
                agroEvent.description = model.description;
                agroEvent.createdBy = model.createdBy;
                agroEvent.tenantId = model.tenantId;
                this.$calendar.fullCalendar('renderEvent', agroEvent, true);
            });
        });
    }

    prepareEvent(event) {
        this.event = event;
        jQuery('#show-event-modal').modal('show');
    }

    remove() {
        this.agroTaskService.remove(this.event.id).subscribe(() => {
            this.$calendar.fullCalendar('removeEvents', this.event.id);
            this.toaStrService.success(this.labelRemoved);
        });
    }

    prepareNewEvent(start, end) {
        this.formSubmitted = false;

        this.eventForm = this.fb.group({
            workTypeId: [null, Validators.required],
            title: [null, Validators.required],
            description: [null]
        });

        this.event = {
            start: start,
            end: end,
            backgroundColor: this.config.colors.success,
            textColor: this.config.colors.default
        };

        jQuery('#create-event-modal').modal('show');
    }

    createEvent() {
        this.formSubmitted = true;

        if (!this.eventForm.valid) {
            this.toaStrService.warning(this.labelValidationFail);
            return;
        }

        Object.assign(this.event, this.eventForm.value);

        this.agroTaskService.save(this.event).subscribe(data => {
            this.event.id = data.id;

            this.toaStrService.success(this.labelSaved);

            this.$calendar.fullCalendar('renderEvent', this.event, true);
            this.$calendar.fullCalendar('unselect');
            jQuery('#create-event-modal').modal('hide');
        });
    }

    private getEventBackgroundColor(workTypeId) {
        switch (workTypeId) {
            case 1:
                return this.config.colors.success;
            case 2:
                return this.config.colors.primary;
            case 3:
                return this.config.colors.warning;
            case 4:
                return this.config.colors.danger;
            case 5:
                return this.config.colors.info;
            case 6:
                return this.config.colors.dark;
            default:
                return this.config.colors.gray;
        }
    }
}
