export interface MenuItem {
    title: string;
    routerLink?: string;
    icon?: string;
    selected?: boolean;
    expanded?: boolean;
    disabled?: boolean;

    subMenu?: MenuItem[];

    target?: string;
    url?: string;
}
