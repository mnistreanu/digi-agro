import {CustomImageRendererModule} from './custom-image-renderer.module';

describe('CustomImageRendererModule', () => {
    let imageRendererModule: CustomImageRendererModule;

    beforeEach(() => {
        imageRendererModule = new CustomImageRendererModule();
    });

    it('should create an instance', () => {
        expect(imageRendererModule).toBeTruthy();
    });
});
