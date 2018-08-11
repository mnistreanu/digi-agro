import {ManageTenantsModule} from './manage-tenants.module';

describe('ManageTenantsModule', () => {
    let manageTenantsModule: ManageTenantsModule;

    beforeEach(() => {
        manageTenantsModule = new ManageTenantsModule();
    });

    it('should create an instance', () => {
        expect(manageTenantsModule).toBeTruthy();
    });
});
