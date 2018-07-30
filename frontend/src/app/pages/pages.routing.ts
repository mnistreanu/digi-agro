import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {PagesComponent} from "./pages.component";
import {SearchComponent} from "./search/search.component";
import {BlankComponent} from "./blank/blank.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {SuperAdminOrAdminGuard} from "../guards/super-admin-or-admin-guard";
import {AdminGuard} from "../guards/admin-guard";
import {AuthGuard} from "../guards/auth-guard";
import {SuperAdminGuard} from "../guards/super-admin-guard";


export const routes: Routes = [
    {
        path: '',
        component: PagesComponent,
        children:[
            { path:'', redirectTo:'dashboard', pathMatch:'full' },
            { path: 'dashboard', loadChildren: 'app/pages/dashboard/dashboard.module#DashboardModule', data: { breadcrumb: 'Dashboard' }  },
            { path: 'maps', loadChildren: 'app/pages/maps/maps.module#MapsModule', data: { breadcrumb: 'Maps' } },
            { path: 'charts', loadChildren: 'app/pages/charting/charting.module#ChartingModule', data: { breadcrumb: 'Charts' } },
            { path: 'ui', loadChildren: 'app/pages/ui/ui.module#UiModule', data: { breadcrumb: 'UI' } },
            { path: 'tools', loadChildren: 'app/pages/tools/tools.module#ToolsModule', data: { breadcrumb: 'Tools' } },
            { path: 'mail', loadChildren: 'app/pages/mail/mail.module#MailModule', data: { breadcrumb: 'Mail' } },
            { path: 'calendar', loadChildren: 'app/pages/calendar/calendar.module#CalendarModule', data: { breadcrumb: 'Calendar' } },
            { path: 'form-elements', loadChildren: 'app/pages/form-elements/form-elements.module#FormElementsModule', data: { breadcrumb: 'Form Elements' } },
            { path: 'tables', loadChildren: 'app/pages/tables/tables.module#TablesModule', data: { breadcrumb: 'Tables' } },
            { path: 'editors', loadChildren: 'app/pages/editors/editors.module#EditorsModule', data: { breadcrumb: 'Editors' } },
            { path: 'search', component: SearchComponent, data: { breadcrumb: 'Search' } },
            { path: 'blank', component: BlankComponent, data: { breadcrumb: 'Blank page' } },
            { path: 'user-profile', component: UserProfileComponent, data: { breadcrumb: 'User Profile' } },
            { path: 'manage-users', loadChildren: 'app/pages/manage-users/manage-users.module#ManageUsersModule', data: { breadcrumb: 'Manage Users' }, canActivate: [SuperAdminOrAdminGuard] },
            { path: 'manage-brands', loadChildren: 'app/pages/manage-brands/manage-brands.module#ManageBrandsModule', data: { breadcrumb: 'Manage Brands' }, canActivate: [AdminGuard] },
            { path: 'manage-machines', loadChildren: 'app/pages/manage-machines/manage-machines.module#ManageMachinesModule', data: { breadcrumb: 'Manage Machines' }, canActivate: [AdminGuard] },
            { path: 'telemetry', loadChildren: 'app/pages/telemetry/telemetry.module#TelemetryModule', data: { breadcrumb: 'Telemetry' }, canActivate: [AuthGuard] },

            { path: 'manage-tenants', loadChildren: 'app/pages/manage-tenants/manage-tenants.module#ManageTenantsModule', data: { breadcrumb: 'Manage Tenants' }, canActivate: [SuperAdminGuard] },
            { path: 'manage-branches', loadChildren: 'app/pages/manage-branches/manage-branches.module#ManageBranchesModule', data: { breadcrumb: 'Manage Branches' }, canActivate: [AdminGuard] },
            { path: 'agro-task-calendar', loadChildren: 'app/pages/agro-task-calendar/agro-task-calendar.module#AgroTaskCalendarModule', data: { breadcrumb: 'Agro Task Calendar' }, canActivate: [AuthGuard] },
            { path: 'notification-subscription', loadChildren: 'app/pages/notifications/subscription/notification-subscription.module#NotificationSubscriptionModule', data: { breadcrumb: 'Notification subscriptions' }, canActivate: [AuthGuard] },
            { path: 'notification-list', loadChildren: 'app/pages/notifications/list/notification-list.module#NotificationListModule', data: { breadcrumb: 'Notifications' }, canActivate: [AuthGuard] }
        ]
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);