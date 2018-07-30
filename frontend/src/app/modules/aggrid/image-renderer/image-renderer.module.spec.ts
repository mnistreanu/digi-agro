import { ImageRendererModule } from './image-renderer.module';

describe('ImageRendererModule', () => {
  let imageRendererModule: ImageRendererModule;

  beforeEach(() => {
    imageRendererModule = new ImageRendererModule();
  });

  it('should create an instance', () => {
    expect(imageRendererModule).toBeTruthy();
  });
});
