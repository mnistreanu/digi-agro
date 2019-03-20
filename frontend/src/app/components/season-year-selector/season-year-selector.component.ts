import {Component, OnInit} from '@angular/core';
import {CropSeasonService} from '../../services/crop/crop-season.service';

@Component({
    selector: 'app-season-year-selector',
    templateUrl: './season-year-selector.component.html',
    styleUrls: ['./season-year-selector.component.scss']
})
export class SeasonYearSelectorComponent implements OnInit {

    label: number;

    availableYears: number[];
    seasonYear: number;

    constructor(private cropSeasonService: CropSeasonService) {
    }

    ngOnInit() {
        this.setup();
        this.cropSeasonService.seasonsChanged.subscribe(() => this.setup());

        // just for test
        this.cropSeasonService.seasonYearChanged.subscribe((year) => console.log('Year Changed - ' + year));
    }

    private setup() {
        console.log('Setup season-year-selector');
        this.cropSeasonService.getYears().subscribe(years => {
            this.availableYears = years;
            this.completeSelection();
        });
    }

    private completeSelection() {
        if (!this.availableYears || this.availableYears.length == 0) {
            this.seasonYear = null;
        }
        else {
            if (this.seasonYear == null) {
                this.completeDefaultSelection();
            }
            else {
                if (!this.availableYears.some((candidate) => candidate == this.seasonYear)) {
                    this.completeDefaultSelection();
                }
            }
        }
    }

    private completeDefaultSelection() {
        // select last year
        this.seasonYear = this.availableYears[this.availableYears.length - 1];
        this.onSeasonYearChanged();
    }

    public onSeasonYearChanged() {
        this.cropSeasonService.changeSeasonYear(this.seasonYear);
    }
}
