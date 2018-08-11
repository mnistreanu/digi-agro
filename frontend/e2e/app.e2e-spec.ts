import {AzCliPage} from './app.po';

describe('app-cli App', () => {
    let page: AzCliPage;

    beforeEach(() => {
        page = new AzCliPage();
    });

    it('should display message saying app works', () => {
        page.navigateTo();
        expect(page.getParagraphText()).toEqual('app works!');
    });
});
