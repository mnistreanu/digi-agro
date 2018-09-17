import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {PestListComponent} from './manage-pests/pest-list/pest-list.component';
import { PestComponent} from './manage-pests/pest/pest.component';
import {PesticideListComponent} from './manage-pesticides/pesticide-list/pesticide-list.component';
import {PesticideComponent} from './manage-pesticides/pesticide/pesticide.component';
import {FertilizerListComponent} from './manage-fertilizers/fertilizer-list/fertilizer-list.component';
import {FertilizerComponent} from './manage-fertilizers/fertilizer/fertilizer.component';

const routes: Routes = [
    {path: 'pest-list', component: PestListComponent},
    {path: 'pest/:id', component: PestComponent, data: {breadcrumb: 'Pest Form'}},

    {path: 'pesticide-list', component: PesticideListComponent},
    {path: 'pesticide/:id', component: PesticideComponent, data: {breadcrumb: 'Pesticide Form'}},

    {path: 'fertilizer-list', component: FertilizerListComponent},
    {path: 'fertilizer/:id', component: FertilizerComponent, data: {breadcrumb: 'Fertilizer Form'}}
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        RouterModule.forChild(routes),
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [PestListComponent, PestComponent,
        PesticideListComponent, PesticideComponent, FertilizerListComponent, FertilizerComponent]
})
export class ChemicalsPestsModule {
}
