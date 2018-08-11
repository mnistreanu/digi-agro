import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormErrorBlockComponent} from './form-error-block.component';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule
    ],
    declarations: [FormErrorBlockComponent],
    exports: [FormErrorBlockComponent]
})

export class FormErrorBlockModule {
}
