package microservices.book.multiplication.service;

/**
 * author arks 12-May-2021
 *
 */
public interface RandomGeneratorService {
   /**
    * @return a randomly-generated factor. It's always a number between 11 and 99.
    */
    int generateRandomFactor();
}
