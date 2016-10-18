package main

import (
	"bufio"
	"fmt"
	"log"
	"net"
	"os"
	"os/signal"
)

var (
	clients = map[string]client{}
)

func main() {
	listener, err := net.Listen("tcp", ":8000")
	if err != nil {
		fmt.Printf("net.Listen: %s\n", err)
		os.Exit(1)
	}
	defer listener.Close()

	c := make(chan os.Signal, 1)
	signal.Notify(c, os.Interrupt)
	go func() {
		for range c {
			for _, cli := range clients {
				cli.conn.Close()
			}
			log.Print("Vihozu")
			os.Exit(0)
		}
	}()

	for {
		conn, err := listener.Accept()
		log.Printf("accepted connection from %s", conn.RemoteAddr())
		if err != nil {
			fmt.Printf("Listener.Accept: %s\n", err)
			continue
		}

		go handleConn(client{
			conn:  conn,
			inbox: make(chan string),
		})
	}

}

func registerClient(conn net.Conn) *client {
	input := bufio.NewScanner(conn)
	input.Scan()
	if input.Err() != nil {
		fmt.Printf("registerClient: %s\n", input.Err())
		return nil
	}
	return client{
		userName:input.Text(),
		conn:conn,
		inbox:make(chan string),
	}
}

type client struct {
	userName string
	conn     net.Conn
	inbox    chan string
}

func (cli client) listenForInbox() {
	//fmt.Printf("listen %q\n", cli.userName)
	for msg := range cli.inbox {
		//fmt.Printf("listenForInbox: send %q to %q\n", msg, cli.userName)
		err := cli.send(msg)
		if err != nil {
			fmt.Printf("listenForInbox: %s\n", err)
			break
		}
	}
}

func (cli client) send(msg string) error {
	_, err := fmt.Fprintln(cli.conn, msg)
	return err
}

func handleConn(cli client) {
	defer cli.conn.Close()
	var userName, receiver string
	input := bufio.NewScanner(cli.conn)

	for input.Scan() {
		if userName == "" {
			userName = input.Text()
			cli.userName = userName
			clients[userName] = cli
			go cli.listenForInbox()
			//fmt.Printf("userName=%s\n", userName)
			continue
		}
		if receiver == "" {
			receiver = input.Text()
			//fmt.Printf("receiver=%s\n", receiver)
			continue
		}
		fmt.Printf("sending %q to %q from %q\n", input.Text(), receiver, userName)
		go func() {
			if receiverSess, ok := clients[receiver]; ok {
				//fmt.Printf("send %q to %q\n", input.Text(), receiver)
				receiverSess.inbox <- input.Text()
			} else {
				fmt.Printf("%q is currently offline\n", receiver)
			}
		}()

	}

	if input.Err() != nil {
		log.Printf("exit from handleConn %v", cli)
		fmt.Printf("handleConn %s\n", input.Err())
		return
	}
}
