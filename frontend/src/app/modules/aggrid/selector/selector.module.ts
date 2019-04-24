import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {GroupedSelectorComponent} from './grouped-selector/grouped-selector.component';
import {SelectorComponent} from './single-selector/selector.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [GroupedSelectorComponent, SelectorComponent],
    exports: [GroupedSelectorComponent, SelectorComponent]
})
export class SelectorModule {
}
