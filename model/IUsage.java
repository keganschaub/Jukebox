package model;
/**
 * Objects from a class that implements this interface can be used to determine
 * if something has been done a specified number of times on the current date.
 */
public interface IUsage {

  /**
   * Use this object once if possible.
   * 
   * @return true if the max usage has not been reached and record a usage on
   *         the current date. Return false if the max usages has been reached
   */
  public abstract boolean use();

  /**
   * Check to see How often has use() been called successfully
   * 
   * @return How often use() has been called.
   */
  public abstract int timesUsed();

  /**
   * How often this object will allow it use.
   * 
   * @return The maximum number of usages (how often use can b called
   * */
  public abstract int maxTimesForUse();

  /**
   * Use this to know if the max usage times has been reached
   * 
   * @return True if use can be called again
   */
  public abstract boolean canUse();

}