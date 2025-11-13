public class QueueElement<T>
 {
    public T data;
    float priority;
        
    public QueueElement(T e, float prio){
                   data = e;
                   priority = prio;
   }
}