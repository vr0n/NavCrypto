package navcrypto;

import java.util.*;
/**
 * Write a description of class Crypto here.
 *
 * @author (your name)
 * @version 121917
 */
public class Crypto
{
    private final char [] alphabet = {
        'A',
        'A',
        'A',
        'B',
        'B',
        'B',
        'C',
        'C',
        'C',
        'D',
        'D',
        'D',
        'E',
        'E',
        'E',
        'F',
        'F',
        'F',
        'G',
        'G',
        'G',
        'H',
        'H',
        'H',
        'I',
        'I',
        'I',
        'J',
        'J',
        'J',
        'K',
        'K',
        'K',
        'L',
        'L',
        'L',
        'M',
        'M',
        'M',
        'N',
        'N',
        'O',
        'O',
        'O',
        'P',
        'P',
        'P',
        'Q',
        'R',
        'R',
        'R',
        'S',
        'S',
        'T',
        'T',
        'T',
        'U',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z',
        
    };
    private final String [] navAlphabet = {
        //based on http://phoneticalphabets.net/Navajo_Phonetic_Alphabet.html
        "WOL-LA-CHEE",      //A
        "BE-LA-SANA",       //A
        "TSE-NILL",         //A
        "NA-HASH-CHID",     //B
        "SHUSH",            //B
        "TOISH-JEH",        //B
        "MOASI",            //C
        "TLA-GIN",          //C
        "BA-GOSHI",         //C
        "BE",               //D
        "CHINDI",           //D
        "LHA-CHA-EH",       //D
        "AH-JAH",           //E
        "DZEH",             //E
        "AH-NAH",           //E
        "CHUO",             //F
        "TSA-E-DONIN-EE",   //F
        "MA-E",             //F
        "AH-TAD",           //G
        "KLIZZIE",          //G
        "JEHA",             //G
        "TSE-GAH",          //H
        "CHA",              //H
        "LIN",              //H
        "TKIN",             //I
        "YEH-HES",          //I
        "A-CHI",            //I
        "TKELE-CHO-G",      //J
        "AH-YA-TSINNE",     //J
        "YIL-DOI",          //J
        "JAD-HO-LONI",      //K
        "BA-AH-NE-DI-TININ",//K
        "KLIZZIE-YAZZIE",   //K
        "DIBEH-YAZZIE",     //L
        "AH-JAD",           //L
        "NASH-DOIE-TSO",    //L
        "TSIN-TLITI",       //M
        "BE-TAS-TNI",       //M
        "NA-AS-TSO-SI",     //M
        "TSAH",             //N
        "A-CHIN",           //N
        "A-KHA",            //O
        "TLO-CHIN",         //O
        "NE-AHS-JAH",       //O
        "CLA-GI-AIH",       //P
        "BI-SO-DIH",        //P
        "NE-ZHONI",         //P
        "CA-YEILTH",        //Q
        "GAH",              //R
        "DAH-NES-TSA",      //R
        "AH-LOSZ",          //R
        "DIBEH",            //S
        "KLESH",            //S
        "D-AH",             //T
        "A-WOH",            //T
        "THAN-ZIE",         //T
        "SHI-DA",           //U
        "NO-DA-IH",         //U
        "A-KEH-DI-GLINI",   //V
        "GLOE-IH",          //W
        "AL-NA-AS-DZOH",    //X
        "TSAH-AS-ZIH",      //Y
        "BESH-DO-TLIZ"      //Z
    };
    private String code;
    private String textToEncode;
    private String textToDecode;
    private StringBuilder finishedCode;
    private String interim = "";
    
    public Crypto()
    {
        textToEncode = " ";
        textToDecode = " ";
    }
    public Crypto(String text)
    {
        code = text;
        textToEncode = " ";
        textToDecode = " ";
    }
    public Crypto(String text, char direction)
    {
        if(direction == 'd' || direction == 'D')
        {
            textToDecode = text;
            this.decode(textToDecode);
        }
        else if(direction == 'e' || direction == 'E')
        {
            textToEncode = text;
            this.encode(textToEncode);
        }
        else if(direction == 'x' || direction == 'X')
        {
            textToEncode = text;
            this.superEncode(textToEncode);
        }
        else if(direction == 'z' || direction == 'Z')
        {
            textToDecode = text;
            this.superDecode(textToDecode);
        }
        else
        {
            textToEncode = " ";
            textToDecode = " ";
        }
    }
    private void decode(String text)
    {
        int tokenCounter;
        String tokenHolder;
        
        StringTokenizer textToken;
        textToken = new StringTokenizer(text);
        
        tokenCounter = textToken.countTokens();
        
        finishedCode = new StringBuilder();
        
        for(int k=0; k<tokenCounter; k++)
        {
            tokenHolder = textToken.nextToken().toUpperCase();
            for(int i=0; i<navAlphabet.length; i++)
            {
                if(tokenHolder.equals(navAlphabet[i]))
                {
                    finishedCode.append(alphabet[i]);
                }
            }
        }    
    }
    private void encode(String text)
    {
        finishedCode = new StringBuilder();
        for(int k=0; k<text.length(); k++)
        {
            if(text.charAt(k) != ' ')
            {
                for(int j=0; j<alphabet.length; j++)
                {
                    if(Character.toUpperCase(text.charAt(k)) == alphabet[j])
                    {
                        finishedCode.append(navAlphabet[j] + " ");
                        j = alphabet.length;
                    }
                }
            }
            else if(text.charAt(k) == ' ')
            {
                finishedCode.append(" ");
            }
        }
    }
    public String reverse()
    {
        StringBuilder textToReverse;
        textToReverse = new StringBuilder(code);
        textToReverse.reverse();
        return textToReverse.toString();
    }
    
    public String analyze(String text)
    {
        String analysis = new String();
        int[] regAlphabet = new int[26];
        int blocks = 1;
        double average = 0.0;
        int sum = 0;
        
        if(text.length() == 0)
        {
            return null;
        }
        
        for(int i = 0; i < text.length(); i++)
        {
            if(getASCII(text.charAt(i)) <= 25 && getASCII(text.charAt(i)) >= 0)
            {
                regAlphabet[getASCII(text.charAt(i))]++;
            }
            else if(text.charAt(i) == ' ')
            {
                blocks++;
            }
        }
        
        analysis += "Words: " + blocks + "\n";
        
        for(int i = 0; i < regAlphabet.length; i++)
        {
            if(regAlphabet[i] != 0)
            {
                sum += regAlphabet[i];
                
                analysis +=  ((char)(i + 65)) + ": " 
                        + regAlphabet[i] + "\n";
            }
        }
        
        average = (double) sum /(double) blocks;
        
        analysis += "Average characters per word: " + average + "\n";
        
        return analysis;
    }
    private void superEncode(String text)
    {
        Random numberGenerator;
        numberGenerator = new Random();
        
        int capsTest = 0;
        
        finishedCode = new StringBuilder();
        for(int k=0; k<text.length(); k++)
        {
            if(text.charAt(k) != ' ')
            {
                for(int j=0; j<alphabet.length; j++)
                {
                    if(Character.toUpperCase(text.charAt(k)) == alphabet[j])
                    {
                        finishedCode.append(navAlphabet[j] + " ");
                        j = alphabet.length;
                    }
                }
            }
            else if(text.charAt(k) == ' ')
            {
                finishedCode.append(" ");
            }           
        }
        for(int k=0; k<finishedCode.length(); k++)
        {           
            if(finishedCode.charAt(k) == '-')
            {
                finishedCode.delete(k, (k+1));
            }
        }
        
        finishedCode = removeBlankSpace(finishedCode);
        
        finishedCode = finishedCode.reverse();
        
        interim = VignereEncrypt(finishedCode.toString());
        
        finishedCode.setLength(0);
        finishedCode.append(interim);
        
        for(int k=0; k<finishedCode.length(); k++)
        {
            capsTest = numberGenerator.nextInt(2);
            if(capsTest == 1)
            {
                finishedCode.setCharAt(k, Character.toUpperCase(finishedCode.charAt(k)));
            }
            else
            {
                finishedCode.setCharAt(k, Character.toLowerCase(finishedCode.charAt(k)));
            }
        }
    }
    private void superDecode(String text)
    {
        StringBuilder holder = new StringBuilder();
        text = this.VignereDecrypt(text);
        holder.setLength(0);
        holder.append(text);
        
        holder = holder.reverse();
        //finishedCode = holder;
        finishedCode = navTree(holder);
    }
    private StringBuilder removeBlankSpace(StringBuilder sb){
        int j = 0;
        for(int i = 0; i < sb.length(); i++) {
            if (!Character.isWhitespace(sb.charAt(i))) {
            sb.setCharAt(j++, sb.charAt(i));
            }
        }
        sb.delete(j, sb.length());
        
        return sb;
    } 
    static String VignereEncrypt(String text) {
        String key = "NAVAJO";
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
    static String VignereDecrypt(String text) {
        String key = "NAVAJO";
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
    public String getFinishedCode()
    {
        return finishedCode.toString();
    }
    private StringBuilder navTree(StringBuilder sb)
    {
        StringBuilder newString = new StringBuilder();
        for(int k=0; k<sb.length(); k++)
        {
            //'a' is finished
            if(sb.charAt(k) == 'a' || sb.charAt(k) == 'A')
            {
        	 // t - A-WOH
                 if(sb.charAt(k+1) == 'w' || sb.charAt(k+1) == 'W')
        	 {
        	     newString.append('T');
        	     k += 3;
        	 }
        	 // x - AL-NA-AS-DZOH
        	 else if(sb.charAt(k+1) == 'l' || sb.charAt(k+1) == 'L')
        	 {
        	     newString.append('X');
        	     k += 9;
        	 }
        	 // v - A-KEH-DI-GLINI
        	 // o - A-KHA
        	 else if(sb.charAt(k+1) == 'k' || sb.charAt(k+1) == 'K')
        	 {
        	     if(sb.charAt(k+2) == 'e' || sb.charAt(k+2) == 'E')
        	     {
        	         newString.append('V');
        	         k += 10;
        	     }
        	     else
        	     {
        	         newString.append('O');
        	         k += 3;
        	     }
        	 }
        	 // e - AH-JAH	
        	 // l - AH-JAD
        	 // r - AH-LOSZ
        	 // e - AH-NAH
        	 // g - AH-TAD		
        	 // j - AH-YA-TSINNE
        	 else if(sb.charAt(k+1) == 'h' || sb.charAt(k+1) == 'H')
        	 {
        	     if(sb.charAt(k+2) == 'y' || sb.charAt(k+2) == 'Y')
        	     {
        	         newString.append('J');
        	         k += 9;
        	     }
        	     else if(sb.charAt(k+2) == 't' || sb.charAt(k+2) == 'T')
        	     {
        	         newString.append('G');
        	         k += 4;
        	     }
        	     else if(sb.charAt(k+2) == 'n' || sb.charAt(k+2) == 'N')
        	     {
        	         newString.append('E');
        	         k += 4;
        	     }
        	     else if(sb.charAt(k+2) == 'l' || sb.charAt(k+2) == 'L')
        	     {
        	         newString.append('R');
        	         k += 5;
        	     }
        	     else
        	     {
        	         if(sb.charAt(k+4) == 'h' || sb.charAt(k+4) == 'H')
        	         {
        	             newString.append('E');
        	             k += 4;
        	         }
        	         else
        	         {
        	             newString.append('L');
        	             k += 4;
        	         }
        	     }
        	 }
        	 // i - A-CHI
        	 // n - A-CHIN
        	 else if(sb.charAt(k+1) == 'c' || sb.charAt(k+1) == 'C')
        	 {
        	     if(!(sb.charAt(k+4) == 'n') || !(sb.charAt(k+4) == 'N'))
        	     {
        	         newString.append('I');
        	         k += 3;
        	     }
        	     else
        	     {
        	         newString.append('N');
        	         k += 4;
        	     }
        	 }
            }
            //'b' is finished
            else if(sb.charAt(k) == 'b' || sb.charAt(k) == 'B')
            {
                // k - BA-AH-NE-DI-TININ
        	// c - BA-GOSHI
        	if(sb.charAt(k+1) == 'a' || sb.charAt(k+1) == 'A')
        	{
        	    if(sb.charAt(k+2) == 'a' || sb.charAt(k+2) == 'A')
        	    {
        	        newString.append('K');
        	        k += 12;
        	    }
        	    else
        	    {
        	        newString.append('C');
        	        k += 6;
        	    }
        	}
        	// d - BE
        	// a - BE-LA-SANA
        	// z - BESH-DO-TLIZ
        	// m - BE-TAS-TNI
        	else if(sb.charAt(k+1) == 'e' || sb.charAt(k+1) == 'E')
        	{
        	    if(sb.charAt(k+2) == 'l' || sb.charAt(k+2) == 'L')
        	    {
        	        newString.append('A');
        	        k += 7;
        	    }
        	    else if(sb.charAt(k+2) == 's' || sb.charAt(k+2) == 'S')
        	    {
        	        newString.append('Z');
        	        k += 9;
        	    }
        	    else if(sb.charAt(k+2) == 't' || sb.charAt(k+2) == 'T')
        	    {
        	        newString.append('M');
        	        k += 7;
        	    }
        	    else
        	    {
        	        newString.append('D');
        	        k += 1;
        	    }
        	}
        	// p - BI-SO-DIH
        	else
        	{
        	    newString.append('P');
        	    k += 6;
        	}
            }
            //'c' is finished
            else if(sb.charAt(k) == 'c' || sb.charAt(k) == 'C')
            {
                // q - CA-YEILTH
                if(sb.charAt(k+1) == 'a' || sb.charAt(k+1) == 'A')
                {
                    newString.append('Q');
	            k += 7;
                }
        	// h - CHA
        	// d - CHINDI
        	// f - CHUO
        	else if(sb.charAt(k+1) == 'h' || sb.charAt(k+1) == 'H')
                {
                    if(sb.charAt(k+2) == 'a' || sb.charAt(k+2) == 'A')
                    {
                        newString.append('H');
	                k += 2;
                    }
                    else if(sb.charAt(k+2) == 'i' || sb.charAt(k+2) == 'I')
                    {
                        newString.append('D');
	                k += 5;
                    }
                    else
                    {
                        newString.append('F');
	                k += 3;
                    }
                }
        	// p - CLA-GI-AIH
        	else
                {
                    newString.append('P');
	            k += 7;
                }
            }
            //'d' is finished
            else if(sb.charAt(k) == 'd' || sb.charAt(k) == 'D')
            {
                // e - DZEH
                if(sb.charAt(k+1) == 'z' || sb.charAt(k+1) == 'Z')
                {
                    newString.append('E');
	            k += 3;
                }
        	// l - DIBEH-YAZZIE
        	// r - DAH-NES-TSA
        	else if(sb.charAt(k+2) == 'h' || sb.charAt(k+2) == 'H')
                {
                    if(k+3 < sb.length())
                    {
                        if(sb.charAt(k+3) == 'n' || sb.charAt(k+3) == 'N')
                        {
                            newString.append('R');
    	                    k += 8;
                        }
                        else
                        {
                            newString.append('T');
    	                    k += 2;
                        }
                    }
                    else
                    {
                        newString.append('T');
    	                k += 2;
                    }
                }
        	// s - DIBEH
        	// t - D-AH
        	else
            	{
            	    if(k+5 < sb.length())
            	    {
            	        if(sb.charAt(k+5) == 'y' || sb.charAt(k+5) == 'Y')
                        {
                            newString.append('L');
    	                    k += 10;
                        }
                    }
                    else
                    {
                        newString.append('S');
        	        k += 4;
                    }
        	}
            }
            //'g' is finished
            else if(sb.charAt(k) == 'g' || sb.charAt(k) == 'G')
            {
                //r - GAH
	        //w - GLOE-IH
	        if(sb.charAt(k+1) == 'a' || sb.charAt(k+1) == 'A')
	        {
	            newString.append('R');
	            k += 2;
	        }
	        else
	        {
	            newString.append('W');
	            k += 5;
	        }
            }
            //'j' is finished
            else if(sb.charAt(k) == 'j' || sb.charAt(k) == 'J')
            {
                //g - JEHA
                if(sb.charAt(k+1) == 'e' || sb.charAt(k+1) == 'E')
                {
                    newString.append('G');
                    k += 3;
                }
	        //k - JAD-HO-LONI
	        else
	        {
	            newString.append('K');
                    k += 8;
	        }
            }
            //'k' is finished
            else if(sb.charAt(k) == 'k' || sb.charAt(k) == 'K')
            {
                // g - KLIZZIE
        	// k - KLIZZIE-YAZZIE
        	// s - KLESH
        	if(sb.charAt(k+2) == 'e' || sb.charAt(k+2) == 'E')
        	{
        	    newString.append('S');
                    k += 4;
        	}
        	else if(sb.charAt(k+7) == 'y' || sb.charAt(k+7) == 'Y')
        	{
        	    newString.append('K');
                    k += 12;
        	}
        	else
        	{
        	    newString.append('G');
                    k += 6;
        	}
            }
            //'l' is finished
            else if(sb.charAt(k) == 'l' || sb.charAt(k) == 'L')
            {
                //d - LHA-CHA-EH
                if(sb.charAt(k+1) == 'h' || sb.charAt(k+1) == 'H')
                {
                    newString.append('D');
                    k += 7;
                }
	        //h - LIN
	        else
	        {
	            newString.append('H');
                    k += 2;
	        }
            }
            //'m' is finished
            else if(sb.charAt(k) == 'm' || sb.charAt(k) == 'M')
            {
                //c - MOASI
                if(sb.charAt(k+1) == 'o' || sb.charAt(k+1) == 'O')
                {
                    newString.append('C');
                    k += 4;
                }
	        //f - MA-E
	        else
	        {
	            newString.append('F');
                    k += 2;
	        }
            }
            //'n' is finished
            else if(sb.charAt(k) == 'n' || sb.charAt(k) == 'N')
            {
                // b - NA-HASH-CHID
        	// l - NASH-DOIE-TSO
        	// m - NA-AS-TSO-SI
        	if(sb.charAt(k+1) == 'a' || sb.charAt(k+1) == 'A')
        	{
        	    if(sb.charAt(k+2) == 'h' || sb.charAt(k+2) == 'H')
        	    {
        	        newString.append('B');
        	        k += 9;
        	    }
        	    else if(sb.charAt(k+2) == 's' || sb.charAt(k+2) == 'S')
        	    {
        	        newString.append('L');
        	        k += 10;
        	    }
        	    else
        	    {
        	        newString.append('M');
        	        k += 8;
        	    }
        	}
        	// o - NE-AHS-JAH
        	// p - NE-ZHONI
        	else if(sb.charAt(k+1) == 'e' || sb.charAt(k+1) == 'E')
        	{
        	    if(sb.charAt(k+2) == 'a' || sb.charAt(k+2) == 'A')
        	    {
        	        newString.append('O');
        	        k += 7;
        	    }
        	    else
        	    {
        	        newString.append('P');
        	        k += 6;
        	    }
        	}
        	// u - NO-DA-IH 
        	else
        	{
        	    newString.append('U');
        	    k += 5;
        	}
            }
            //'s' is finished
            else if(sb.charAt(k) == 's' || sb.charAt(k) == 'S')
            {
                //b - SHUSH
                if(sb.charAt(k+2) == 'u' || sb.charAt(k+2) == 'U')
                {
                    newString.append('B');
                    k += 4;
                }
	        //u - SHI-DA
	        else
	        {
	            newString.append('U');
                    k += 4;
	        }
            }
            //'t' is finished
            else if(sb.charAt(k) == 't' || sb.charAt(k) == 'T')
            {
                // t - THAN-ZIE
                if(sb.charAt(k+1) == 'h' || sb.charAt(k+1) == 'H')
                {
                    newString.append('T');
                    k += 6;
                }
        	// j - TKELE-CHO-G
        	// i - TKIN
        	else if(sb.charAt(k+1) == 'k' || sb.charAt(k+1) == 'K')
                {
                    if(sb.charAt(k+2) == 'e' || sb.charAt(k+2) == 'E')
                    {
                        newString.append('J');
                        k += 8;
                    }
                    else
                    {
                        newString.append('I');
                        k += 3;
                    }
                }
        	// c - TLA-GIN
        	// o - TLO-CHIN
        	else if(sb.charAt(k+1) == 'l' || sb.charAt(k+1) == 'L')
                {
                    if(sb.charAt(k+2) == 'o' || sb.charAt(k+2) == 'O')
                    {
                        newString.append('O');
                        k += 6;
                    }
                    else
                    {
                        newString.append('C');
                        k += 5;
                    }
                }
        	// b - TOISH-JEH
        	else if(sb.charAt(k+1) == 'o' || sb.charAt(k+1) == 'O')
                {
                    newString.append('B');
                    k += 7;
                }
        	// f - TSA-E-DONIN-EE
        	// n - TSAH
        	// y - TSAH-AS-ZIH
        	// h - TSE-GAH
        	// a - TSE-NILL
        	// m - TSIN-TLITI
        	else if(sb.charAt(k+1) == 's' || sb.charAt(k+1) == 'S')
                {
                    if(sb.charAt(k+2) == 'i' || sb.charAt(k+2) == 'I')
                    {
                        newString.append('M');
                        k += 8;
                    }
                    else if(sb.charAt(k+2) == 'e' || sb.charAt(k+2) == 'E')
                    {
                        if(sb.charAt(k+3) == 'g' || sb.charAt(k+3) == 'G')
                        {
                            newString.append('H');
                            k += 5;
                        }
                        else
                        {
                            newString.append('A');
                            k += 6;
                        }
                    }
                    else if(sb.charAt(k+2) == 'a' || sb.charAt(k+2) == 'A')
                    {
                        if(sb.charAt(k+4) == 'd' || sb.charAt(k+4) == 'D')
                        {
                            newString.append('F');
                            k += 10;
                        }
                        else if((sb.charAt(k+4) == 'a' || sb.charAt(k+4) == 'A') && (sb.charAt(k+5) == 's' || sb.charAt(k+5) == 'S'))
                        {
                            newString.append('Y');
                            k += 8;
                        }
                        else
                        {
                            newString.append('N');
                            k += 3;
                        }
                    }
                }
            }
            //'w' is finished
            else if(sb.charAt(k) == 'w' || sb.charAt(k) == 'W')
            {
                //a - WOL-LA-CHEE
                newString.append('A');
                k += 8;
            }
            //'y' is finished
            else if(sb.charAt(k) == 'y' || sb.charAt(k) == 'Y')
            {
                //i - YEH-HES
	        //j - YIL-DOI
	        if(sb.charAt(k+1) == 'e' || sb.charAt(k+1) == 'E')
	        {
	            newString.append('I');
	            k += 5;
	        }
	        else
	        {
	            newString.append('J');
	            k += 5;
	        }
            }
        }
        
        return newString;   
    }
    
    private int getASCII(char l) {
        return Character.toUpperCase(l) - 65;
    }
}
