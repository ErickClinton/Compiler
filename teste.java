public class teste {
    void cad (List<Token> tokenLst) {
        if (tokenLst.get (curPos) == Token.DIG) {
          grCadeiaDig ();
          curPos++;
          return;
        }
        if (tokenLst.get (curPos) != Token.EOF) {
            if (tokenLst.get (curPos) == Token.MAIS) {
                curPos++;
                cad (tokenLst);
                grCadeiaMais ();
                } else if (tokenLst.get (curPos) == Token.MENOS) {
                curPos++;
                cad (tokenLst);
                grCadeiaMenos ();
            } else {
                  erroSin (tokenLst, curPos);
            }
        }
    }
}
