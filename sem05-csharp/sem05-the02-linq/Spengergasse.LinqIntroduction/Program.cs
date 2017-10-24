using System;
using System.Collections.Generic;
using System.Linq;
using LinqInAction.LinqBooks.Common;

namespace Spengergasse.LinqIntroduction {
  class Program {
    static void Main(string[] args) {
      PrintTitle("Exercise 1");

      ObjectDumper.Write(
        from b in SampleData.Books
        where b.Price > 0
        orderby b.Price descending
        select new { b.Isbn, b.Title, Preis2 = b.Price }
      );

      PrintHeading("1. PageCount > 200");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.PageCount > 200
        select new { book.Isbn, book.Title, book.Price, book.PageCount }
      );

      PrintHeading("2. Published before August 2007");

      ObjectDumper.Write(
        from books in SampleData.Books
        where books.PublicationDate < new DateTime(2007, 8, 1)
        orderby books.Title
        select new { books.PublicationDate, books.Title, books.Isbn }
      );

      PrintHeading("3. Books published by FunBooks");

      ObjectDumper.Write(
        from books in SampleData.Books
        where books.Publisher.Name == "FunBooks"
        orderby books.Title
        select new { books.Isbn, books.Title, books.Publisher.Name }
      );

      PrintHeading("4. Subject 'Software development'");

      ObjectDumper.Write(
        from books in SampleData.Books
        where books.Subject.Name == "Software development"
        orderby books.Title
        select new { books.Title, books.Subject.Description }
      );

      PrintHeading("5. Publishers with descriptions");

      ObjectDumper.Write((
        from book in SampleData.Books
        where book.Subject.Name == "Software development"
        orderby book.Title
        select new {
          book.Publisher.Name,
          book.Publisher.Website,
          book.Subject.Description
        }
      ).Distinct());

      PrintHeading("6. Software development books");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.Subject.Name == "Software development"
        orderby book.Title
        select new {
          book.Title,
          book.PageCount,
          Authors = book.Authors
            .Select(author => author.FirstName + " " + author.LastName)
            .Aggregate((pre, cur) => pre + ", " + cur)
        }
      );

      PrintHeading("6. Software development books with two from's");

      ObjectDumper.Write(
        from book in SampleData.Books
        from author in book.Authors
        where book.Subject.Name == "Software development"
        orderby book.Title
        select new {
          book.Title,
          book.PageCount,
          Authors = author.FirstName + " " + author.LastName
        }
      );

      PrintHeading("7. Books by Octavio Prince");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.Authors.Any(author =>
          author.FirstName == "Octavio"
          && author.LastName == "Prince"
        )
        select new { book.Title, book.PageCount }
      );

      PrintHeading("8. Books by Octavio Prince as main author");

      ObjectDumper.Write(
        from book in SampleData.Books
        let first = book.Authors.First()
        where first.FirstName == "Octavio"
          && first.LastName == "Prince"
        select new { book.Title, book.PageCount }
      );

      PrintHeading("9. Publisher with book counts");

      ObjectDumper.Write(
        from book in SampleData.Books
        group book by book.Publisher into g
        select new {
          g.Key.Name,
          Count = g.Count(),
          AveragePrice = Math.Round(g.Average(b => b.Price)),
          Pages = g.Sum(b => b.PageCount)
        }
      );

      PrintHeading("10. Books with author counts");

      ObjectDumper.Write(
        from book in SampleData.Books
        select new {
          Publisher = book.Publisher.Name,
          book.Title,
          Authors = book.Authors.Count()
        }
      );

      PrintHeading("11. Subjects with books");

      var subjects =
        from book in SampleData.Books
        group book by book.Subject;

      foreach (var subjectGroup in subjects) {
        System.Console.WriteLine(subjectGroup.Key.Name);
        ObjectDumper.Write(subjectGroup.Select(
          book => new { book.Isbn, book.Title }
        ));
        System.Console.WriteLine();
      }

      PrintTitle("Exercise 2");

      PrintHeading("1. Books with more than 1 author");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.Authors.Count() > 1
        select new { book.Title, book.Authors }, 1
      );

      PrintHeading("2. Books about software development");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.Subject.Name == "Software development"
        select new { book.Title, book.Authors }, 1
      );

      PrintHeading("3. Reviews by Fred");

      ObjectDumper.Write(
        from review in SampleData.Reviews
        where review.User.Name == "Fred"
        select new { review.Book.Title, review.Comments }
      );

      PrintHeading("4. Books ordered by ratings");

      ObjectDumper.Write(
        from book in SampleData.Books
        let rating = book.Reviews.Select(r => r.Rating).Average()
        orderby rating descending
        select new { book.Title, rating }
      );

      PrintHeading("5. Book(s) with worst rating");

      ObjectDumper.Write(
        from book in SampleData.Books
        let worst = SampleData.Reviews.Min(r => r.Rating)
        where book.Reviews.Select(r => r.Rating).Contains(worst)
        select new { book.Title, Rating = worst }
      );

      PrintHeading("6. Reviews per user");

      ObjectDumper.Write(
        from review in SampleData.Reviews
        group review by review.User into reviewsPerUser
        select new {
          reviewsPerUser.Key.Name,
          Count = reviewsPerUser.Count()
        }
      );

      System.Console.WriteLine();
    }

    static void PrintHeading(string heading) {
      System.Console.WriteLine("\n{0}\n---\n", heading);
    }
    static void PrintTitle(string title) {
      System.Console.WriteLine(
        "\n{0}\n{1}\n", title, new String('=', title.Length)
      );
    }
  }
}
