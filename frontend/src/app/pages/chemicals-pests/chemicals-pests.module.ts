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
import {HarmfulOrganismListComponent} from './manage-harmful-organisms/harmful-organism-list/harmful-organism-list.component';
import { HarmfulOrganismComponent} from './manage-harmful-organisms/harmful-organism/harmful-organism.component';
import {PesticideListComponent} from './manage-pesticides/pesticide-list/pesticide-list.component';
import {PesticideComponent} from './manage-pesticides/pesticide/pesticide.component';
import {FertilizerListComponent} from './manage-fertilizers/fertilizer-list/fertilizer-list.component';
import {FertilizerComponent} from './manage-fertilizers/fertilizer/fertilizer.component';

const routes: Routes = [
    {path: 'harmful-organism-list', component: HarmfulOrganismListComponent},
    {path: 'pesticide-list', component: PesticideListComponent},
    {path: 'fertilizer-list', component: FertilizerListComponent},
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
    declarations: [HarmfulOrganismListComponent, HarmfulOrganismComponent,
        PesticideListComponent, PesticideComponent, FertilizerListComponent, FertilizerComponent]
})
export class ChemicalsPestsModule {
}
