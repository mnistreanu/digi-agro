import { DatePipe } from '@angular/common';

export class DateUtil {

  public static formatDateWithTime(date: Date): string {
    let datePipe = new DatePipe("en-US");
    return datePipe.transform(date, 'dd/MM/yyyy hh:mm');
  }


  public static formatDate(date: Date): string {
    let datePipe = new DatePipe("en-US");
    return datePipe.transform(date, 'dd/MM/yyyy');
  }

}
