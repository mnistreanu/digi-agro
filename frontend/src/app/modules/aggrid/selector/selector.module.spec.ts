import {GroupedSelectorModule} from './grouped-selector.module';

describe('SelectorModule', () => {
    let groupedSelectorModule: GroupedSelectorModule;

    beforeEach(() => {
        groupedSelectorModule = new GroupedSelectorModule();
    });

    it('should create an instance', () => {
        expect(groupedSelectorModule).toBeTruthy();
    });
});
