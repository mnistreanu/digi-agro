import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {NavigateRendererComponent} from "./navigate-renderer.component";

@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [NavigateRendererComponent],
  exports: [NavigateRendererComponent]
})

export class NavigateRendererModule { }
