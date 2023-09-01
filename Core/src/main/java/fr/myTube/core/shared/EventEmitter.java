package fr.myTube.core.shared;




public abstract class EventEmitter<T extends Event> {

  protected EventHandler<T> eventHandler;

  public EventEmitter(EventHandler<T> eventHandler){
    this.eventHandler = eventHandler;
  }
  public void emit(T event){
    new Thread(() -> this.eventHandler.handle(event)).start();
  }

}
