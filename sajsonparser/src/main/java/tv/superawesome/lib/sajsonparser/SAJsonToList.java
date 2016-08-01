package tv.superawesome.lib.sajsonparser;

/**
 * Created by gabriel.coman on 01/08/16.
 */
public interface SAJsonToList<A, B> {
    A traverseItem(B param);
}
