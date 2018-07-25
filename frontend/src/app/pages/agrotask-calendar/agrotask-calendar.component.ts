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
            timezone: 'local',
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
            eventClick: (event) => this.showEvent(event),
            eventResize: (event) => this.onEventTimeChange(event),
            eventDrop: (event) => this.onEventTimeChange(event),
            editable: true,
            droppable: true,
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

    showEvent(event) {

        this.event = event;
        this.formSubmitted = false;
        this.eventForm = this.fb.group({
            workTypeId: [this.event.workTypeId, Validators.required],
            title: [this.event.title, Validators.required],
            description: [this.event.description]
        });

        jQuery('#event-modal').modal('show');
    }

    prepareNewEvent(start, end) {
        let allDay = !start.hasTime() && !end.hasTime();

        let event = {
            start: start,
            end: end,
            backgroundColor: this.config.colors.success,
            textColor: this.config.colors.default,
            allDay: allDay
        };

        this.showEvent(event);
    }

    saveEvent() {
        this.formSubmitted = true;

        if (!this.eventForm.valid) {
            this.toaStrService.warning(this.labelValidationFail);
            return;
        }

        Object.assign(this.event, this.eventForm.value);
        this.event.backgroundColor = this.getEventBackgroundColor(this.event.workTypeId);
        let isNew = this.event.id == null;

        this.agroTaskService.save(this.event).subscribe(data => {
            this.event.id = data.id;

            this.toaStrService.success(this.labelSaved);

            if (isNew) {
                this.$calendar.fullCalendar('renderEvent', this.event, true);
            }
            else {
                this.$calendar.fullCalendar('updateEvent', this.event);
            }

            this.$calendar.fullCalendar('unselect');
            jQuery('#event-modal').modal('hide');
        });
    }

    remove() {
        this.agroTaskService.remove(this.event.id).subscribe(() => {
            this.$calendar.fullCalendar('removeEvents', this.event.id);
            this.toaStrService.success(this.labelRemoved);
        });
    }

    private onEventTimeChange(event) {
        let id = event.id;
        let start = event.start;
        let end = event.end;
        this.agroTaskService.changeEventTime(id, start, end).subscribe(() => {
            this.toaStrService.success(this.labelSaved);
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
