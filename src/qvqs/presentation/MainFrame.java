package qvqs.presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import qvqs.application.WordManager;

public class MainFrame extends JFrame {

	public MainFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("94% Helper");

		final JPanel content = new JPanel();

		content.setLayout(new GridBagLayout());
		getContentPane().add(content);

		final JLabel patterLbl = new JLabel("Pattern : ");
		final GridBagConstraints gbc_patternLbl = new GridBagConstraints();
		gbc_patternLbl.gridx = 0;
		gbc_patternLbl.gridy = 0;
		gbc_patternLbl.insets = new Insets(2, 2, 2, 2);
		content.add(patterLbl, gbc_patternLbl);

		final JTextField patternTf = new JTextField();
		final GridBagConstraints gbc_patternTf = new GridBagConstraints();
		gbc_patternTf.gridx = 1;
		gbc_patternTf.gridy = 0;
		gbc_patternTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_patternTf.weightx = 1.0;
		gbc_patternTf.insets = new Insets(2, 2, 2, 2);
		content.add(patternTf, gbc_patternTf);

		final JLabel valuesLbl = new JLabel("Valeurs : ");
		final GridBagConstraints gbc_valuesLbl = new GridBagConstraints();
		gbc_valuesLbl.gridx = 0;
		gbc_valuesLbl.gridy = 1;
		gbc_valuesLbl.insets = new Insets(2, 2, 2, 2);
		content.add(valuesLbl, gbc_valuesLbl);

		final JTextField valuesTf = new JTextField();
		final GridBagConstraints gbc_valuesTf = new GridBagConstraints();
		gbc_valuesTf.gridx = 1;
		gbc_valuesTf.gridy = 1;
		gbc_valuesTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_valuesTf.weightx = 1.0;
		gbc_valuesTf.insets = new Insets(2, 2, 2, 2);
		content.add(valuesTf, gbc_valuesTf);

		final JList<String> list = new JList<String>();
		list.setBorder(new TitledBorder("Résultats"));
		list.setOpaque(false);

		final DefaultListModel<String> model = new DefaultListModel<>();

		list.setModel(model);

		final GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridx = 0;
		gbc_list.gridy = 2;
		gbc_list.gridwidth = 2;
		gbc_list.weightx = 1.0;
		gbc_list.weighty = 1.0;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.insets = new Insets(2, 2, 2, 2);
		content.add(list, gbc_list);

		final JButton but = new JButton("Résoudre !!!");
		final GridBagConstraints gbc_but = new GridBagConstraints();
		gbc_but.gridx = 0;
		gbc_but.gridy = 3;
		gbc_but.gridwidth = 2;
		gbc_but.fill = GridBagConstraints.BOTH;
		gbc_but.insets = new Insets(2, 2, 2, 2);
		content.add(but, gbc_but);

		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<String> tmp = WordManager.getInstance().findWordForPattern(patternTf.getText().trim(), valuesTf.getText().trim());

				model.clear();

				for (String res : tmp) {

					model.addElement(res);
					System.out.println(res);
				}

				list.repaint();

			}
		});

		pack();
	}
}
