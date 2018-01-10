using System;

namespace myApp
{
    class Program
    {
        static void Main(string[] args)
        {
            int private_add(int a, int b) 
            {
                return a + b;
            }

            Console.WriteLine("Hello World!  This is taking a long time to set up in Visual Code...");
            Console.WriteLine(typeof(string).Assembly.ImageRuntimeVersion);
            Console.WriteLine("Using private_add: {0}", private_add(3, 7));
            Console.WriteLine("Calling from singleLineFn: {0}", singleLineFn(19, 22));
            // Console.WriteLine("Here's a binary value starting with an underscore" + 0b_0101_0101);
        }

        public static int singleLineFn(int a, int b) => a + b;
    }
}
