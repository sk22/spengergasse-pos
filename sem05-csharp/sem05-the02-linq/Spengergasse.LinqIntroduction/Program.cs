using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml.Linq;
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

      var worst = SampleData.Reviews.Min(r => r.Rating);
      ObjectDumper.Write(
        from book in SampleData.Books
        where book.Reviews.Any(r => r.Rating == worst)
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

      PrintTitle("XML Exercises");

      PrintHeading("0. Example");

      Console.WriteLine(new XElement(
        "Buecher",
        from b in SampleData.Books
        where b.PageCount > 100
        orderby b.Title
        select new XElement("Buch", b.Title)
      ));

      PrintHeading("1. All books with data elements");

      System.Console.WriteLine(new XElement(
        "Buecher",
        from b in SampleData.Books
        orderby b.Title
        select new XElement(
          "Buch",
          new XElement("Titel", b.Title),
          new XElement("Seiten", b.PageCount),
          new XElement("Isbn", b.Isbn)
        )
      ));

      PrintHeading("2. All books with data attributes");

      System.Console.WriteLine(new XElement(
        "Buecher",
        from b in SampleData.Books
        orderby b.Title
        select new XElement(
          "Buch",
          new XAttribute("Titel", b.Title),
          new XAttribute("Seiten", b.PageCount),
          new XAttribute("Isbn", b.Isbn)
        )
      ));

      PrintHeading("3. Nested data");

      System.Console.WriteLine(new XElement(
        "Buecher",
        from p in SampleData.Publishers
        select new XElement(
          "Verlag",
          new XAttribute("Name", p.Name),
          from b in SampleData.Books
          where p == b.Publisher
          select new XElement(
            "Buch",
            new XAttribute("Titel", b.Title),
            from a in b.Authors
            select new XElement("Autor", a.LastName)
          )
        )
      ));

      PrintTitle("Exercise 2");

      PrintHeading("7. Users with reviews");

      System.Console.WriteLine(new XElement(
        "Users",
        from u in SampleData.Users
        orderby u.Name
        select new XElement(
          "User",
          new XAttribute("Name", u.Name),
          from r in SampleData.Reviews
          where r.User == u
          select new XElement("Review", r.Comments)
        )
      ));

      PrintHeading("8. Authors with books and review counts");

      System.Console.WriteLine(new XElement(
        "Authors",
        SampleData.Authors.Select(a => new XElement(
          "Author",
          new XAttribute("Name", a.LastName + " " + a.FirstName),
          SampleData.Books
            .Where(b => b.Authors.Contains(a))
            .Select(b => new XElement(
              "Book",
              new XAttribute("Title", b.Title),
              new XAttribute("ReviewCount", b.Reviews.Count())
            ))
        ))
      ));

      PrintHeading("9. Publishers with reviews");

      System.Console.WriteLine(new XElement(
        "Publishers",
        SampleData.Publishers.Select(p => new XElement(
          "Publisher",
          new XAttribute("Name", p.Name),
          SampleData.Reviews
            .Where(r => r.Book.Publisher == p)
            .Select(r => new XElement(
              "Review",
              new XAttribute("Title", r.Book.Title),
              new XAttribute("Rating", r.Rating),
              r.Comments
            ))
        ))
      ));

      PrintHeading("Publishers with average rating");

      System.Console.WriteLine(new XElement(
        "Publishers",
        SampleData.Publishers
          // filtering publishers without reviews
          .Where(p =>
            SampleData.Reviews.Where(r => r.Book.Publisher == p).Any()
          )
          .Select(p => new XElement(
            "Publisher",
            new XAttribute("Name", p.Name),
            new XAttribute(
              "AvgRating",
              SampleData.Reviews
                .Where(r => r.Book.Publisher == p)
                .Select(r => r.Rating)
                .Average()
            )
          ))
      ));

      Console.WriteLine();
    }

    static void PrintHeading(string heading) {
      Console.WriteLine("\n{0}\n---\n", heading);
    }
    static void PrintTitle(string title) {
      System.Console.WriteLine(
        "\n{0}\n{1}\n", title, new String('=', title.Length)
      );
    }
  }
}
