import {ImageRendererModule} from './image-renderer.module';

describe('CustomImageRendererModule', () => {
    let imageRendererModule: ImageRendererModule;

    beforeEach(() => {
        imageRendererModule = new ImageRendererModule();
    });

    it('should create an instance', () => {
        expect(imageRendererModule).toBeTruthy();
    });
});
