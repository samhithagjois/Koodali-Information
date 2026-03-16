package koodali.service.exceptions;

public class SectionNotFoundException extends RuntimeException {

    public SectionNotFoundException() {
        super("""
                The section does not exist.
                Please check that your input format follows the follwoing RegEx:
                [BDEILMNPX][ELNRUX]_[BIAN][UE_D][LG123V].
                The first two letters are the country/city code, the next three letters are the batch.Please talk to Samhitha if you mess up.""");
    }
}
