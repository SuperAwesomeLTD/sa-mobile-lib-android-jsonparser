package tv.superawesome.lib.sajsonparser;

/**
 * Created by gabriel.coman on 01/08/16.
 */
public interface SAListToJson <A, B> {
    A traverseItem(B param);
}
