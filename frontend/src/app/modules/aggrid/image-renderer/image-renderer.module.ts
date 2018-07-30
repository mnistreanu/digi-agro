import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImageRendererComponent } from './image-renderer.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ImageRendererComponent],
  exports: [ImageRendererComponent]
})
export class ImageRendererModule { }
