import {Component, OnInit} from '@angular/core';
import {AppConfig} from '../../app.config';
import {ReminderService} from '../../services/reminder.service';
import 'style-loader!fullcalendar/dist/fullcalendar.min.css';
import {ReminderModel} from './reminder.model';
import {LangService} from '../../services/lang.service';
import {AgroWorkTypeModel} from './agro-work-type.model';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../common/messages';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../services/alert.service';

@Component({
    selector: 'app-reminder',
    templateUrl: './reminder.component.html',
    styleUrls: ['./reminder.component.scss']
})
export class ReminderComponent implements OnInit {

    config: any;

    calendarOptions: any;
    $calendar: any;
    dragOptions: Object = {zIndex: 999, revert: true, revertDuration: 0};

    workTypeModels: AgroWorkTypeModel[];
    reminderModels: ReminderModel[];

    event: any = {};
    eventForm: FormGroup;
    formSubmitted = false;

    constructor(private appConfig: AppConfig,
                private fb: FormBuilder,
                private reminderService: ReminderService,
                private alertService: AlertService,
                private langService: LangService) {
    }

    ngOnInit(): void {
        this.config = this.appConfig.config;

        this.setupCalendarOptions();
        this.$calendar = jQuery('#calendar');
        this.$calendar.fullCalendar(this.calendarOptions);
        jQuery('.draggable').draggable(this.dragOptions);
        this.findAgroWorkTypes();
        this.findReminders();
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
                const today = new Date().toDateString();
                const compareDate = date.toDate().toDateString();
                if (today == compareDate) {
                    cell.css('background-color', '#ccc');
                }
            }
        };
    }

    private findAgroWorkTypes() {
        this.reminderService.findWorkTypes().subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.workTypeModels = payloadModel.payload;
        });
    }

    private findReminders() {
        this.reminderService.find().subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.reminderModels = payloadModel.payload || [];

            this.reminderModels.forEach((model) => {
                const event: any = {};
                event.id = model.id;
                event.workTypeId = model.workTypeId;
                event.title = model.title;
                event.start = new Date(model.starting);
                event.end = new Date(model.ending);
                event.backgroundColor = this.getEventBackgroundColor(event.workTypeId);
                event.textColor = this.config.colors.default;
                event.description = model.description;
                event.createdBy = model.createdBy;
                event.tenantId = model.tenantId;
                this.$calendar.fullCalendar('renderEvent', event, true);
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
        const allDay = !start.hasTime() && !end.hasTime();

        const event = {
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
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.event, this.eventForm.value);
        this.event.backgroundColor = this.getEventBackgroundColor(this.event.workTypeId);
        const isNew = this.event.id == null;

        this.reminderService.save(this.event).subscribe(data => {
            this.event.id = data.id;

            this.alertService.saved();

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
        this.reminderService.remove(this.event.id).subscribe(() => {
            this.$calendar.fullCalendar('removeEvents', this.event.id);
            this.alertService.removed();
        });
    }

    private onEventTimeChange(event) {
        const id = event.id;
        const start = event.start;
        const end = event.end;
        this.reminderService.changeEventTime(id, start, end).subscribe(() => {
            this.alertService.saved();
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
