import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PinnedRowRendererComponent} from './pinned-row-renderer.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [PinnedRowRendererComponent],
    exports: [PinnedRowRendererComponent]
})
export class PinnedRowRendererModule {
}
