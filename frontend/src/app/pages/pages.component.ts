import {AfterViewInit, Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Location} from '@angular/common';
import {AppState} from '../app.state';

@Component({
    selector: 'app-pages',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './pages.component.html',
    styleUrls: ['./pages.component.scss'],
    providers: [AppState]
})
export class PagesComponent implements OnInit, AfterViewInit {

    public isMenuCollapsed = false;

    constructor(private _state: AppState,
                private _location: Location) {
        this._state.subscribe('menu.isCollapsed', (isCollapsed) => {
            this.isMenuCollapsed = isCollapsed;
        });
    }

    ngOnInit() {
        this.getCurrentPageName();
    }

    public ngAfterViewInit(): void {
        document.getElementById('pre-loader').style['display'] = 'none';
    }

    public getCurrentPageName(): void {
        const url = this._location.path();
        const hash = (window.location.hash) ? '#' : '';
        setTimeout(function () {
            const subMenu = jQuery('a[href="' + hash + url + '"]').closest('li').closest('ul');
            window.scrollTo(0, 0);
            subMenu.closest('li').addClass('sidebar-item-expanded');
            subMenu.slideDown(250);
        });
    }

    public hideMenu(): void {
        this._state.notifyDataChanged('menu.isCollapsed', true);
    }

}
