export default class Validator {
  constructor() {
  }

  static isNumeric(value) {
    const pattern = /^\d*$/g
    return this.testPattern(pattern, value)
  }

  static isAlphabeticalNumerical(value) {
    const pattern = /^[0-9a-zA-Z]*$/g
    return this.testPattern(pattern, value);
  }

  static testPattern(pattern, value) {
      return pattern.test(value)
    }
}
