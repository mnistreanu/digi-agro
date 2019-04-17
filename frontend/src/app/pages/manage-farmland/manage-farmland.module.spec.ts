import {ManageFarmlandModule} from './manage-farmland.module';
describe('ManageFarmlandModule', () => {
    let manageFarmlandModule: ManageFarmlandModule;

    beforeEach(() => {
        manageFarmlandModule = new ManageFarmlandModule();
    });

    it('should create an instance', () => {
        expect(manageFarmlandModule).toBeTruthy();
    });
});
