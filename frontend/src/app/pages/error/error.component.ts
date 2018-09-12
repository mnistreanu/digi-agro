import {AfterViewInit, Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';
import {ErrorService} from '../../services/error.service';
import {Constants} from '../../common/constants';

@Component({
    selector: 'app-error',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './error.component.html'
})
export class ErrorComponent implements OnInit, AfterViewInit {

    error: Error = null;

    constructor(private router: Router,
                private errorService: ErrorService) {
    }

    ngOnInit(): void {
        this.error = this.errorService.getError();
        if (!this.error) {
            this.router.navigate([Constants.PAGE_AFTER_LOGIN]);
        }
        this.errorService.clearError();
    }

    ngAfterViewInit(): void {
        document.getElementById('pre-loader').style['display'] = 'none';
    }
}
