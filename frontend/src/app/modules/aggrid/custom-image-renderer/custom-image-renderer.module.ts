import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CustomImageRendererComponent} from './custom-image-renderer.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [CustomImageRendererComponent],
    exports: [CustomImageRendererComponent]
})
export class CustomImageRendererModule {
}
