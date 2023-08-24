package fr.myTube.website.core.shared;

public interface Event {
   default  String getEventType(){
     return this.getClass().getSimpleName();
   }
}
