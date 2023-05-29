// Represents a node of a doubly-linked list.
//borrowed from teletext lab
public class Node<E>
{
    private E value;
    private Node<E> previous;
    private Node<E> next;

    // Constructor:
    public Node( E initValue, Node<E> initPrevious,
                      Node<E> initNext )
    {
        value = initValue;
        previous = initPrevious;
        next = initNext;
    }

    public E getValue()
    {
        return value;
    }

    public Node<E> getPrevious()
    {
        return previous;
    }

    public Node<E> getNext()
    {
        return next;
    }

    public void setValue( E theNewValue )
    {
        value = theNewValue;
    }

    public void setPrevious( Node<E> theNewPrev )
    {
        previous = theNewPrev;
    }

    public void setNext( Node<E> theNewNext )
    {
        next = theNewNext;
    }
}
