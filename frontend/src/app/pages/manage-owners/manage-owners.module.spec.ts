import {ManageOwnersModule} from './manage-owners.module';

describe('ManageOwnersModule', () => {
    let manageOwnersModule: ManageOwnersModule;

    beforeEach(() => {
        manageOwnersModule = new ManageOwnersModule();
    });

    it('should create an instance', () => {
        expect(manageOwnersModule).toBeTruthy();
    });
});
