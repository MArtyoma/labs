export default class Event {
  constructor() {
    document.addEventListener('keydown', function(event) {
      console.log(event.key);
    });
  }

  static on(name : string, callback : (event : KeyboardEvent) => void) {
    document.addEventListener('keydown', function(event) {
      console.log(event.key);
      if (event.key !== name) return;
      callback(event);
    });
  }
}
