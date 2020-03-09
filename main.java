package lab5;
import java.util.NoSuchElementException;
public class main {

	public static void main(String[] args) 
	{
		hash<Integer, Integer> map1 = new hash<>();
        for (int i = 0; i < 20; i++)
            map1.Add(i, i);
        
        System.out.println("Элемент по ключу 6: " + map1.GetElementByKey(6));
        map1.DeleteByKey(6);
        System.out.println("Количество элементов послеудаления 6: " + map1.GetNumberOfElements());

        try
        {
            System.out.println("Элемент по ключу 6: " + map1.GetElementByKey(6));
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Нет элемента по ключу 6");
        }

        System.out.println("Количество элементов: " + map1.GetNumberOfElements() + " коэффициент загруженности: " + map1.getC() + " текущий уровень загруженности: " + map1.getCurrentC());

        map1.print();
        map1.setC(0.75);
        map1.print();

        map1.DeleteAll();

        System.out.println();

        map1.Add(1, 7);
        map1.Add(1, 5);

        System.out.println("Количество элементов: " + map1.GetNumberOfElements() + " коэффициент загруженности: " + map1.getC() + " текущий уровень загруженности: " + map1.getCurrentC());

	}

}


