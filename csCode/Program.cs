﻿using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace csCode
{
    class Program
    {
        static void Main(string[] args)
        {
            var mcastAddress = IPAddress.Parse("230.0.0.1");
            var mcastPort = 7766;
            var serSocket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
            serSocket.SetSocketOption(SocketOptionLevel.IP, SocketOptionName.AddMembership,
                new MulticastOption(mcastAddress));

            

            byte[] buffer = null;

            Console.WriteLine("Press ENTER to start sending messages");
            Console.ReadLine();
            var endPoint = new IPEndPoint(mcastAddress, mcastPort);
            for (var i = 0; i <= 8000; i++)
            {
                buffer = Encoding.Unicode.GetBytes(i.ToString());
                serSocket.SendTo(buffer, endPoint);
                Console.WriteLine("Sent " + i);
                Thread.Sleep(1000);
            }

            Console.WriteLine("All Done! Press ENTER to quit.");
            Console.ReadLine();
        }
    }
}
