package algorithms.patterns.kwaymerge;

class Element {

  int listIndex;
  int elementIndex;

  public Element(int listIndex, int elementIndex) {
    this.listIndex = listIndex;
    this.elementIndex = elementIndex;
  }

  public int getListIndex() {
    return listIndex;
  }

  public void setListIndex(int listIndex) {
    this.listIndex = listIndex;
  }

  public int getElementIndex() {
    return elementIndex;
  }

  public void setElementIndex(int elementIndex) {
    this.elementIndex = elementIndex;
  }
}
