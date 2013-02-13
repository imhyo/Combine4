import java.util.*;

public class Combine4
{
	public final static int PLUS = 0;
	public final static int MINUS = 1;
	public final static int MULTIPLY = 2;
	public final static int DIVIDE = 3;

	public static void main(String args[])
	{
		if (args.length < 4)
		{
			System.out.println("Insufficient number of arguments");
			return;
		}
		
		combine(buildInitialList(args));
	}

	public static ArrayList buildInitialList(String args[])
	{
		ArrayList l = new ArrayList();
		
		for (int i = 0; i < 4; i++)
		{
			MyElement e = new MyElement();
			e.s = args[i];
			e.n = Float.parseFloat(e.s);
			l.add(e);
		}
		
		return l;
	}
		
	public static boolean combine(ArrayList l)
	{
		int len = l.size();
		int i, j;

		if (len == 1) return checkFinalResult(l);
		
		for (i = 0; i < len - 1; i++)
		{
			for (j = i + 1; j < len; j++)
			{
				if (calculate(l, i, j, PLUS)) return true;
				if (calculate(l, i, j, MINUS)) return true;
				if (calculate(l, j, i, MINUS)) return true;
				if (calculate(l, i, j, MULTIPLY)) return true;
				if (calculate(l, i, j, DIVIDE)) return true;
				if (calculate(l, j, i, DIVIDE)) return true;
			}
		}
		
		return false;
	}

	public static ArrayList copyList(ArrayList l)
	{
		ArrayList l1 = new ArrayList();
		int len = l.size();
		
		for (int i = 0; i < len; i++)
		{
			MyElement temp = new MyElement();
			temp.s = ((MyElement)(l.get(i))).s;
			temp.n = ((MyElement)(l.get(i))).n;
			l1.add(temp);
		}
		
		return l1;
	}
		
	
	public static boolean calculate(ArrayList l, int a, int b, int op)
	{
		ArrayList l1;
		int i, len;
		MyElement e1, e2;

		l1 = copyList(l);

		e1 = (MyElement)(l1.get(a));
		e2 = (MyElement)(l1.get(b));
		
		switch (op)
		{
			case PLUS:
				e1.n = e1.n + e2.n;
				e1.s = "(" + e1.s + "+" + e2.s + ")";
				break;
				
			case MINUS:
				e1.n = e1.n - e2.n;
				e1.s = "(" + e1.s + "-" + e2.s + ")";
				break;
	
			case MULTIPLY:
				e1.n = e1.n * e2.n;
				e1.s = "(" + e1.s + "*" + e2.s + ")";
				break;
	
			case DIVIDE:
				if (e2.n == 0) return false;
				e1.n = e1.n / e2.n;
				e1.s = "(" + e1.s + "/" + e2.s + ")";
				break;
		}
		
		if (e1.n < 0)
			return false;
		
		l1.remove(b);
		
		return combine(l1);
	}

	public static boolean checkFinalResult(ArrayList l)
	{
		if (((MyElement)l.get(0)).n == 24)
		{
			System.out.println(((MyElement)l.get(0)).s);
			return true;
		}
		else
			return false;
	}
	
}

