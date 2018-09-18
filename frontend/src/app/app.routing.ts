import {Routes, RouterModule, PreloadAllModules, NoPreloading} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';

import {ErrorComponent} from './pages/error/error.component';

export const routes: Routes = [
    {path: '', redirectTo: 'pages', pathMatch: 'full'},
    {path: 'pages', loadChildren: 'app/pages/pages.module#PagesModule'},
    {path: 'login', loadChildren: 'app/pages/login/login.module#LoginModule'},
    {path: 'error', component: ErrorComponent},
    {path: '**', redirectTo: 'pages'}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, {
    // preloadingStrategy: PreloadAllModules,
    preloadingStrategy: NoPreloading,
    // useHash: true
});
