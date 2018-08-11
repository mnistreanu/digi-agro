import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {SlimScrollDirective} from './slim-scroll/slim-scroll.directive';
import {WidgetDirective} from './widget/widget.directive';
import {SkyconDirective} from './skycon/skycon.directive';
import {CounterDirective} from './counter/counter.directive';
import {LiveTileDirective} from './live-tile/live-tile.directive';
import {ProgressAnimateDirective} from './progress-animate/progress-animate.directive';
import {DropzoneUploadDirective} from './dropzone/dropzone.directive';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        SlimScrollDirective,
        WidgetDirective,
        SkyconDirective,
        CounterDirective,
        LiveTileDirective,
        ProgressAnimateDirective,
        DropzoneUploadDirective
    ],
    exports: [
        SlimScrollDirective,
        WidgetDirective,
        SkyconDirective,
        CounterDirective,
        LiveTileDirective,
        ProgressAnimateDirective,
        DropzoneUploadDirective
    ]
})
export class DirectivesModule {
}
