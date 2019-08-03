export default class Validator {
  constructor() {
  }

  static isNumeric(value) {
    if (value === "") {
      return null
    }

    const pattern = /^\d*$/g
    return pattern.test(value)
  }
}
