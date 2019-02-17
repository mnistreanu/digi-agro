import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GroupedSelectorComponent} from './grouped-selector.component';
import {FormsModule} from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [GroupedSelectorComponent],
    exports: [GroupedSelectorComponent]
})
export class GroupedSelectorModule {
}
