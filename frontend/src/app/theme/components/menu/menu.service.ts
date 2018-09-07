import {Injectable} from '@angular/core';
import {AuthService} from '../../../services/auth/auth.service';
import {Authorities} from '../../../common/authorities';
import {MenuItem} from '../../../interfaces/menu-item.iterface';

@Injectable()
export class MenuService {

    constructor(private authService: AuthService) {
    }

    public getMenuItems(): MenuItem[] {

        const isSuperAdmin = this.authService.hasAuthority(Authorities.ROLE_SUPER_ADMIN);
        const isAdmin = this.authService.hasAuthority(Authorities.ROLE_ADMIN);
        const isUser = this.authService.hasAuthority(Authorities.ROLE_USER);


        let menuItems: MenuItem[] = [{
            title: 'nav.dashboard',
            routerLink: 'dashboard',
            icon: 'fa-home',
            selected: false,
            expanded: false
        }];

        if (isSuperAdmin) {
            menuItems.push({
                title: 'nav.tenants',
                routerLink: '/pages/manage-tenants',
                icon: 'fas fa-user-tie',
                selected: false,
                expanded: false
            });
        }

        if (isSuperAdmin || isAdmin) {
            menuItems.push({
                title: 'nav.users',
                routerLink: '/pages/manage-users',
                icon: 'fa-users',
                selected: false,
                expanded: false
            });
        }

        if (isAdmin) {
            menuItems.push({
                title: 'nav.branches',
                routerLink: '/pages/manage-branches',
                icon: 'fas fa-code-branch',
                selected: false,
                expanded: false
            });

            menuItems.push({
                title: 'nav.employees',
                routerLink: '/pages/employee',
                icon: 'fa-users',
                selected: false,
                expanded: false
            });

            menuItems.push({
                title: 'nav.brands',
                routerLink: '/pages/manage-brands',
                icon: 'fa-bold',
                selected: false,
                expanded: false
            });
            menuItems.push({
                title: 'nav.machines',
                routerLink: '/pages/manage-machines',
                icon: 'fas fa-car',
                selected: false,
                expanded: false
            });
        }

        if (isAdmin || isUser) {
            menuItems.push({
                title: 'nav.telemetry',
                routerLink: '/pages/telemetry',
                icon: 'fas fa-globe',
                selected: false,
                expanded: false
            });
        }

        if (isAdmin || isUser) {
            menuItems.push({
                title: 'nav.parcels',
                routerLink: '/pages/parcel',
                icon: 'fa fa-th',
                selected: false,
                expanded: false
            });
        }

        if (isAdmin || isUser) {
            menuItems.push({
                title: 'nav.work-agenda',
                routerLink: '/pages/reminder',
                icon: 'fa-calendar',
                selected: false,
                expanded: false
            });
        }

        // if (isUser) {
        //     menuItems.push({
        //         title: 'nav.crops',
        //         routerLink: '/pages/manage-crops/crop-varieties',
        //         icon: 'fa fa-leaf',
        //         selected: false,
        //         expanded: false
        //         // subMenu: [
        //         //     {
        //         //         title: 'nav.crops-varieties',
        //         //         routerLink: 'crop/crops-varieties',
        //         //     },
        //         // ]
        //     });
        // }

        // if (isSuperAdmin || isAdmin) {
        //     menuItems.push({
        //         title: 'nav.crops',
        //         routerLink: '/pages/manage-crops',
        //         icon: 'fas fa-user-tie',
        //         selected: false,
        //         expanded: false
        //     });
        // }

        if (isUser) {
            menuItems.push({
                title: 'nav.crops',
                routerLink: '/pages/manage-crops/crop-varieties',
                icon: 'fa fa-leaf',
                selected: false,
                expanded: false
            });
        }

        if (isSuperAdmin || isAdmin) {
            menuItems.push({
                title: 'nav.crops',
                routerLink: 'manage-crops',
                icon: 'fas fa-leaf',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'nav.crops',
                        routerLink: 'manage-crops'
                    },
                    {
                        title: 'nav.crops-varieties',
                        routerLink: 'manage-crops/crop-varieties'
                    }
                ]
            });
        }

        if (isAdmin || isUser) {
            menuItems.push({
                title: 'nav.expenses-management',
                routerLink: 'expenses',
                icon: 'fa fa-money',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'nav.agricultural-machinery',
                        routerLink: 'expenses/machinery',
                    },
                    {
                        title: 'nav.fuel',
                        routerLink: 'expenses/fuel',
                    },
                    {
                        title: 'nav.sowing',
                        routerLink: 'expenses/sowing',
                    },
                    {
                        title: 'nav.agricultural-works',
                        routerLink: 'expenses/works',
                    },
                    {
                        title: 'nav.pesticides',
                        routerLink: 'expenses/pesticides',
                    },
                    {
                        title: 'nav.fertilizers',
                        routerLink: 'expenses/fertilizers',
                    },
                ]
            });
        }

        if (isAdmin || isUser) {
            menuItems.push({
                title: 'nav.weather',
                routerLink: 'weather',
                icon: 'fa fa-cloud',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'nav.weather-forecast',
                        routerLink: 'weather/forecast',
                    },
                    {
                        title: 'nav.weather-history',
                        routerLink: 'weather/history',
                    },
                ]
            });
        }

        // other menu items from template
        menuItems = menuItems.concat([
            {
                title: 'Charts',
                routerLink: 'charts',
                icon: 'fa-bar-chart',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Ng2-Charts',
                        routerLink: 'charts/ng2charts',
                    },
                ]
            },
            {
                title: 'nav.forecasts',
                routerLink: 'forecasting',
                icon: 'fa-bar-chart',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'nav.forecast-list',
                        routerLink: 'forecasting',
                    },
                    {
                        title: 'nav.forecasting-charts',
                        routerLink: 'forecasting/charts',
                    },
                    {
                        title: 'nav.costs-forecasting',
                        routerLink: 'forecasting/costs',
                    },
                    {
                        title: 'nav.costs-forecasting-form',
                        routerLink: 'forecasting/costs-form',
                    },
                ]
            },
            {
                title: 'UI Features',
                routerLink: 'ui',
                icon: 'fa-laptop',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Buttons',
                        routerLink: 'ui/buttons'
                    },
                    {
                        title: 'Cards',
                        routerLink: 'ui/cards'
                    },
                    {
                        title: 'Components',
                        routerLink: 'ui/components'
                    },
                    {
                        title: 'Icons',
                        routerLink: 'ui/icons'
                    },
                    {
                        title: 'Grid',
                        routerLink: 'ui/grid'
                    },
                    {
                        title: 'List Group',
                        routerLink: 'ui/list-group'
                    },
                    {
                        title: 'Media Objects',
                        routerLink: 'ui/media-objects'
                    },
                    {
                        title: 'Tabs & Accordions',
                        routerLink: 'ui/tabs-accordions'
                    },
                    {
                        title: 'Typography',
                        routerLink: 'ui/typography'
                    }
                ]
            },
            {
                title: 'Tools',
                routerLink: 'tools',
                icon: 'fa-wrench',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Drag & Drop',
                        routerLink: 'tools/drag-drop'
                    },
                    {
                        title: 'Resizable',
                        routerLink: 'tools/resizable'
                    },
                    {
                        title: 'Toastr',
                        routerLink: 'tools/toaster'
                    }
                ]
            },
            {
                title: 'Mail',
                routerLink: 'mail/mail-list/inbox',
                icon: 'fa-envelope-o',
                selected: false,
                expanded: false
            },
            {
                title: 'Calendar',
                routerLink: 'calendar',
                icon: 'fa-calendar',
                selected: false,
                expanded: false
            },
            {
                title: 'Form Elements',
                routerLink: 'form-elements',
                icon: 'fa-pencil-square-o',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Form Inputs',
                        routerLink: 'form-elements/inputs'
                    },
                    {
                        title: 'Form Layouts',
                        routerLink: 'form-elements/layouts'
                    },
                    {
                        title: 'Form Validations',
                        routerLink: 'form-elements/validations'
                    },
                    {
                        title: 'Form Wizard',
                        routerLink: 'form-elements/wizard'
                    }
                ]
            },
            {
                title: 'Tables',
                routerLink: 'tables',
                icon: 'fa-table',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Basic Tables',
                        routerLink: 'tables/basic-tables'
                    },
                    {
                        title: 'Dynamic Tables',
                        routerLink: 'tables/dynamic-tables'
                    }
                ]
            },
            {
                title: 'Editors',
                routerLink: 'editors',
                icon: 'fa-pencil',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Froala Editor',
                        routerLink: 'editors/froala-editor'
                    },
                    {
                        title: 'Ckeditor',
                        routerLink: 'editors/ckeditor'
                    }
                ]
            },
            {
                title: 'Maps',
                routerLink: 'maps',
                icon: 'fa-globe',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Vector Maps',
                        routerLink: 'maps/vectormaps'
                    },
                    {
                        title: 'Google Maps',
                        routerLink: 'maps/googlemaps'
                    },
                    {
                        title: 'Leaflet Maps',
                        routerLink: 'maps/leafletmaps'
                    }
                ]
            },
            {
                title: 'Pages',
                routerLink: ' ',
                icon: 'fa-file-o',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Login',
                        routerLink: '/login'
                    },
                    {
                        title: 'Register',
                        routerLink: '/register'
                    },
                    {
                        title: 'Blank Page',
                        routerLink: 'blank'
                    },
                    {
                        title: 'Error Page',
                        routerLink: '/pagenotfound'
                    }
                ]
            },
            {
                title: 'Menu Level 1',
                icon: 'fa-ellipsis-h',
                selected: false,
                expanded: false,
                subMenu: [
                    {
                        title: 'Menu Level 1.1',
                        url: '#',
                        disabled: true,
                        selected: false,
                        expanded: false
                    },
                    {
                        title: 'Menu Level 1.2',
                        url: '#',
                        subMenu: [{
                            title: 'Menu Level 1.2.1',
                            url: '#',
                            disabled: true,
                            selected: false,
                            expanded: false
                        }]
                    }
                ]
            },
            {
                title: 'External Link',
                url: 'http://themeseason.com',
                icon: 'fa-external-link',
                selected: false,
                expanded: false,
                target: '_blank'
            }
        ]);


        return menuItems;
    }

}
