package utils;

public class AocArrayUtils {

  public static String[] copyArrayWithoutSpecificElement(String[] array, int index){
    if(index<0)
      return array.clone();

    String[] array2 = new String[array.length - 1];
    int j = 0;
    for(int i=0; i < array.length; i++)
    {
      if(i != index)
        array2[j++] = array[i];
    }
    return array2;
  }
}
