import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;


public class StringProblemSolving {
	
	public static void main(String[] args) {
		String input = "dd(df)a(ghhh)";
		String output = reverseStringWithBalancedParentheses(input);
		System.out.println(output);
	}
	
	private static String reverseStringWithBalancedParentheses(String input) {
		String[] arrOfStr = input.split("[()]+");
		String output = "";
		if (arrOfStr.length < 3) {
			output = input;
		} else {
			for (int i=0; i< arrOfStr.length; i++) {
				if(i%2 == 0) {
					output = output + arrOfStr[i];
				} else {
					output =  output +"(" + new StringBuilder(arrOfStr[i]).reverse().toString() + ")";
				}
			}
		}
		return output;
	}
	
	
	private static String reverseStringWithNotBalancedParentheses(String input) {
		String output = "";
		while(true) {
			// if Input has parentheses and sorted
			if(input.contains("(") && input.contains(")") && input.indexOf("(") < input.indexOf(")")) {
				// get from the first chars and reverse the String in the parentheses
				output = output + input.substring(0,input.indexOf("(")).concat("(" +
						new StringBuilder(input.substring(input.indexOf("(")+1,input.indexOf(")"))).reverse().toString() + ")");
				// Remove the String until the found parentheses
				input = input.substring(input.indexOf(")")+1);
			} else {
				if (input.isEmpty()) output = input;
				else output = output + input;
				break;
			}
		}
		return output;
	}
}
