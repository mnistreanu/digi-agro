import {ManageBrandsModule} from './manage-brands.module';

describe('ManageBrandsModule', () => {
    let manageBrandsModule: ManageBrandsModule;

    beforeEach(() => {
        manageBrandsModule = new ManageBrandsModule();
    });

    it('should create an instance', () => {
        expect(manageBrandsModule).toBeTruthy();
    });
});
