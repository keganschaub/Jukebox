package usage;
/**
 * A Usage object can be used to determine if something
 * has been done twice on the same calendar date. 
 */
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.IUsage;

public class Usage implements IUsage, Serializable {

  private GregorianCalendar theDateUponWhichThisObjectWasConstructed;
  private int max;
  private int timesUsed;

  public Usage(int maxTimeToUse) {
    max = maxTimeToUse;
    timesUsed = 0;
    theDateUponWhichThisObjectWasConstructed = new GregorianCalendar();
  }

  /* (non-Javadoc)
   * @see AnInterface#maxTimesForUse()
   */
  @Override
  public int maxTimesForUse() {
    return max;
  }

  /* (non-Javadoc)
   * @see AnInterface#canUse()
   */
  @Override
  public boolean canUse() {
    return timesUsed() < max;
  }

  /* (non-Javadoc)
   * @see AnInterface#use()
   */
  @Override
  public boolean use() {
    GregorianCalendar today = new GregorianCalendar();
    if(!theSameDate(theDateUponWhichThisObjectWasConstructed,today)){
        timesUsed = 0;
        theDateUponWhichThisObjectWasConstructed = today;
    }
    if (!canUse()){
      return false;
    }
    else {
    	timesUsed++;
    }
    return true;
  }

  private boolean theSameDate(GregorianCalendar one, GregorianCalendar other) {
    return one.get(Calendar.DATE) == other.get(Calendar.DATE)
        && one.get(Calendar.MONTH) == other.get(Calendar.MONTH)
        && one.get(Calendar.YEAR) == other.get(Calendar.YEAR);
  }

  /* (non-Javadoc)
   * @see AnInterface#timesUsed()
   */
  @Override
  public int timesUsed() {
    return timesUsed;
  }

  public void pretendItIsTomorrow() {
    theDateUponWhichThisObjectWasConstructed.add(Calendar.DATE, 1);
  }

}