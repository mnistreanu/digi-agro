declare var $: any;

export class NumericUtil {

  public static isNumeric(number) {
    return $.isNumeric(number);
  }

  public static isInteger(number) {
    if (!this.isNumeric(number)) {
      return false;
    }

    return number % 1 === 0;
  }

}
